DESCRIPTION = "Basic kernel module for multithreading"
LICENSE = "CLOSED"

SRC_URI = "file://kthread.c;subdir=${S} \
            file://Makefile;subdir=${S}"

inherit module

KERNEL_MODULE_PROBECONF = "kthread"

S = "${WORKDIR}/kthread"

COMPATIBLE_MACHINE = "raspberrypi4-64"

do_compile() {
    oe_runmake -C ${STAGING_KERNEL_DIR} M=${S} modules
}

do_install() {
    install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/extra
    install -m 0644 ${S}/kthread.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/extra
}