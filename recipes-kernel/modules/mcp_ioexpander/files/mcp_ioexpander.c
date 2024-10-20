#include "linux/module.h"
#include "linux/init.h"
#include "linux/i2c.h"
#include "linux/mutex.h"
#include "linux/device.h"
#include "linux/list.h"
#include "linux/gpio/driver.h"

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Jacek Schneider");
MODULE_DESCRIPTION("mcp23017 driver");

struct mcp23017_dev
{
    unsigned char*  data;
    struct i2c_client*  client;
    struct mutex*   mcp_mutex;
    struct list_head    device_entry;    
    dev_t       devt;
    unsigned    users;
};

static int mcp23017_probe(struct i2c_client* client, const struct i2c_device_id *id);
static int mcp23017_remove(struct i2c_client* client);

static const struct of_device_id mcp23017_dt_ids[] = 
{
    { .compatible = "waveshare,mcp23017" },
    {},
};
MODULE_DEVICE_TABLE(of, mcp23017_dt_ids);

static const struct i2c_device_id mcp23017_id[] = 
{
    {"mcp23017", 0},
    {},
};
MODULE_DEVICE_TABLE(i2c, mcp23017_id);

static struct i2c_driver mcp23017_i2c_driver = 
{
    .driver =
    {
        .owner = THIS_MODULE,
        .name = "mcp23017",
        .of_match_table = of_match_ptr(mcp23017_dt_ids),
    },
    .probe = mcp23017_probe,
    .remove = mcp23017_remove,
    .id_table = mcp23017_id,
};

static int mcp_gpio_get(struct gpio_chip *chip, unsigned offset) {return 1;};
static void mcp_gpio_set(struct gpio_chip *chip, unsigned offset, int value){;};
static int mcp_gpio_input(struct gpio_chip *chip, unsigned offset){return 1;};
static int mcp_gpio_output(struct gpio_chip *chip, unsigned offset, int value){return 1;};

struct gpio_chip mcp_gpio_chip =
{
    .label = "mcp23017",
    .owner = THIS_MODULE,
    .base = -1,
    .ngpio = 16,
    .can_sleep = true,
    .get = mcp_gpio_get,
    .set = mcp_gpio_set,
    .direction_input = mcp_gpio_input,
    .direction_output = mcp_gpio_output,
};

static int __init ModuleInit(void)
{
    printk("Module initialised");
    return 0;
}

static void __exit ModuleExit(void)
{
    printk("Module exitting...");
}

module_init(ModuleInit);
module_exit(ModuleExit);






