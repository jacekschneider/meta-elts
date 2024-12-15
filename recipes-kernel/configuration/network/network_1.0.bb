DESCRIPTION = "Enable I2C support"
LICENSE = "CLOSED"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Specify kernel fragment
SRC_URI += "file://inet.cfg"
KERNEL_CONFIG_FRAGMENTS += "${WORKDIR}/inet.cfg"

ALLOW_EMPTY:${PN} = "1"