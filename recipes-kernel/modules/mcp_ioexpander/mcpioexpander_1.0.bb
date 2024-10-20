DESCRIPTION = "mcp gpio i2c expander module"
LICENSE = "CLOSED"

SRC_URI = "file://mcp_ioexpander.c \
            file://Makefile"

inherit module

KERNEL_MODULE_PROBECONF = "mcp_ioexpander"

S = "${WORKDIR}"

COMPATIBLE_MACHINE = "raspberrypi4-64"

do_compile() {
    oe_runmake -C ${STAGING_KERNEL_DIR} M=${S} modules
}

do_install() {
    install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/extra
    install -m 0644 ${S}/mcp_ioexpander.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/extra
}