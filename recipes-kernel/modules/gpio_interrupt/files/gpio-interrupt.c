#include <linux/kernel.h>
#include <linux/init.h>
#include <linux/module.h>
#include <linux/kdev_t.h>
#include <linux/fs.h>
#include <linux/slab.h>
#include <linux/uaccess.h>
#include <linux/interrupt.h>
#include <ams/io.h>
#include <linux/err.h>

#define IRQ_NUMBER 50


MODULE_LICENSE("GPL");
MODULE_AUTHOR("Jacek Schneider <schneiderautomatyka@gmail.com>");
MODULE_DESCRIPTION("Basic kernel module - gpio interrupt");
MODULE_VERSION("1.0.0");