DESCRIPTION = "Enable I2C support on Raspberry Pi"
LICENSE = "CLOSED"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

DEPENDS += "dtc-native"

FILES_${PN} += " \
    /boot \
    /boot/overlays \
"

# Ensure i2c-bcm2708 overlay is applied for I2C support
do_install:append() {
    install -d ${D}/boot
    if ! grep -q "dtparam=i2c_arm=on" ${D}/boot/config.txt 2>/dev/null; then
        echo "dtparam=i2c_arm=on" >> ${D}/boot/config.txt
    fi
}


# Specify kernel fragment
SRC_URI += "file://i2c.cfg"
KERNEL_CONFIG_FRAGMENTS += "${WORKDIR}/i2c.cfg"


# Add custom DTS file
SRC_URI += "file://custom-i2c-overlay.dts"

# Compile DTS to DTBO
do_compile:append() {
    # Ensure output directory exists
    mkdir -p ${B}/overlays

    # Compile DTS to DTBO
    dtc -I dts -O dtb -o ${B}/overlays/custom-i2c-overlay.dtbo \
        ${WORKDIR}/custom-i2c-overlay.dts
}

# Install DTBO and config.txt
do_install:append() {
    # Ensure boot and overlays directories exist
    install -d ${D}/boot/overlays

    # Deploy the compiled DTBO
    install -m 0644 ${B}/overlays/custom-i2c-overlay.dtbo \
        ${D}/boot/overlays/
}