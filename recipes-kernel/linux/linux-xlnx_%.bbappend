FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " file://bsp.cfg \
                   file://0001-fix-for-s25fl127s.patch "
KERNEL_FEATURES:append = " bsp.cfg"
