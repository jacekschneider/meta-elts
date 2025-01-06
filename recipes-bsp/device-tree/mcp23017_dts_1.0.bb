DESCRIPTION = "Custom Device Tree Overlay"
LICENSE = "CLOSED"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "file://mcp23017.dts"

do_compile() {
    dtc -I dts -O dtb -o ${WORKDIR}/mcp23017.dtbo ${WORKDIR}/mcp23017.dts
}

do_install() {
    install -d ${D}/boot/overlays
    install -m 0644 ${WORKDIR}/mcp23017.dtbo ${D}/boot/overlays/
}

FILES_${PN} = "/boot/overlays/mcp23017.dtbo"