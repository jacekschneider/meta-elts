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

#define INTCAPA	0x8
#define INTCAPB	0x18

struct mcp23017_dev
{
    struct i2c_client*  client;
    struct gpio_chip chip;
};

static inline struct mcp23017_dev *to_mcp23017_dev(struct gpio_chip *gc)
{
	return container_of(gc, struct mcp23017_dev, chip);
}

static int mcp_gpio_get(struct gpio_chip *chip, unsigned offset) 
{
    s32 value;
    struct mcp23017_dev* mcp = to_mcp23017_dev(chip);
    unsigned bank = offset / 8;
    unsigned bit = offset % 8;

    u8 reg_intcap = (bank == 0) ? INTCAPA : INTCAPB;
    value = i2c_smbus_read_byte_data(mcp->client, reg_intcap);
    return (value >= 0) ? (value >> bit) & 0x1 : 0;
}
static void mcp_gpio_set(struct gpio_chip *chip, unsigned offset, int value){;};
static int mcp_gpio_input(struct gpio_chip *chip, unsigned offset){return 1;};
static int mcp_gpio_output(struct gpio_chip *chip, unsigned offset, int value){return 1;};

static int mcp23017_probe(struct i2c_client* client, const struct i2c_device_id *id){
    printk("Mcp23017 probe\n");
    struct mcp23017_dev* mcp;

    if(!i2c_check_functionality(client->adapter, I2C_FUNC_SMBUS_BYTE_DATA))
        return -EIO;
    mcp = devm_kzalloc(&client->dev, sizeof(*mcp), GFP_KERNEL);
    if(!mcp)
        return -ENOMEM;

    mcp->chip.label = client->name;
    mcp->chip.base  = -1;
    mcp->chip.owner = THIS_MODULE;
    mcp->chip.ngpio = 16;
    mcp->chip.can_sleep = true;
    mcp->chip.get = mcp_gpio_get;
    mcp->chip.set = mcp_gpio_set;
    mcp->chip.direction_input = mcp_gpio_input;
    mcp->chip.direction_output = mcp_gpio_output;
    mcp->client = client;
    i2c_set_clientdata(client, mcp);

    return gpiochip_add(&mcp->chip);
};

static int mcp23017_remove(struct i2c_client* client){
    struct mcp23017_dev *mcp;
	mcp = i2c_get_clientdata(client);
	gpiochip_remove(&mcp->chip);
    return 0;
};

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



module_i2c_driver(mcp23017_i2c_driver)







