DESCRIPTION = "Enable I2C support"
LICENSE = "CLOSED"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Specify kernel fragment
SRC_URI += "file://i2c.cfg"
KERNEL_CONFIG_FRAGMENTS += "${WORKDIR}/i2c.cfg"

ALLOW_EMPTY:${PN} = "1"