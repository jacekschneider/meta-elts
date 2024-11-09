DESCRIPTION = "Enable I2C support on Raspberry Pi"
LICENSE = "CLOSED"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Ensure i2c-bcm2708 overlay is applied for I2C support
do_install:append() {
    mkdir -p ${D}${sysconfdir}  # Ensure the /etc directory exists
    echo "dtparam=i2c_arm=on" >> ${D}${sysconfdir}/config.txt
}

# Specify kernel fragment
SRC_URI += "file://i2c.cfg"
KERNEL_CONFIG_FRAGMENTS += "${WORKDIR}/i2c.cfg"