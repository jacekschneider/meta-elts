#include <linux/module.h>
#include <linux/init.h>
#include <linux/kthread.h>
#include <linux/sched.h>
#include <linux/delay.h>

static const unsigned int t_size = 5;
static struct task_struct* p_threads[5];

int thread_function(void* thread_nr);

MODULE_LICENSE("GPL");
MODULE_AUTHOR("JSCH");
MODULE_DESCRIPTION("Threads in LKM");

static int __init ModuleInit(void)
{
    printk("Initialising threads\n");
    for(size_t i = 1; i < t_size; i++)
    {
        p_threads[i] = kthread_create(thread_function, &i, "kthread_%ld", i);
        if(p_threads[i] != NULL)
        {
            wake_up_process(p_threads[i]);
            printk("kthread  - Thread %ld was created and is running now!\n", i);
        }
        else
        {
            printk("kthread - Thread %ld could not be created!\n", i);
        }
    }
    return 0;
}

static void __exit ModuleExit(void)
{
    printk("kthread - Stops the threads");
    for (size_t i=1; i<t_size; i++)
    {
        if (p_threads[i] != NULL)
        {
            kthread_stop(p_threads[i]);
            printk("kthread - Stopping %ld thread", i);
        }
    }
}

module_init(ModuleInit);
module_exit(ModuleExit);

int thread_function(void* thread_nr)
{
    unsigned i = 0;
    size_t t_nr = *(size_t *) thread_nr;

    while(!kthread_should_stop())
    {
        printk("kthread - Thread %ld is executed! Counter val: %d\n", t_nr, i++);
        if (t_nr <= 0)
        {
            printk("kthread - wrong t_nr!\n");
            t_nr = 1;
        }
        msleep(t_nr * 1000);
    }
    printk("kthread - Thread %ld finished execution!\n", t_nr);
    return 0;
}