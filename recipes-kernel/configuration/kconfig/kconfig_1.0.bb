DESCRIPTION = "Enable I2C support"
LICENSE = "CLOSED"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Specify kernel fragment
SRC_URI += "file://inkernel_config.cfg"
KERNEL_CONFIG_FRAGMENTS += "${WORKDIR}/inkernel_config.cfg"

ALLOW_EMPTY:${PN} = "1"