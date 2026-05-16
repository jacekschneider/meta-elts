FILESEXTRAPATHS:prepend := "${THISDIR}/${MACHINE}:"

SRC_URI:zynq-generic:append = " file://system-user.dtsi \
    file://system.dtb \
    "

require ${@'device-tree-sdt.inc' if d.getVar('SYSTEM_DTFILE') != '' else ''}
CONFIG_DTFILE:zynq-generic = "system.dtb"
DT_INCLUDE:append = " ${WORKDIR}"

SRC_URI:rpi4-elts:append = " file://system.dts \
    "
CONFIG_DTFILE:rpi4-elts = "system.dts"