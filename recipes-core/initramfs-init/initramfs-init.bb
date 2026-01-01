SUMMARY = "Minimal /init for initramfs"
LICENSE = "CLOSED"

SRC_URI = "file://init"

S = "${WORKDIR}"

do_install() {
    install -d ${D}
    install -m 0755 ${WORKDIR}/init ${D}/init
    # avoid CRLF issues if edited on Windows
    sed -i -e 's/\r$//' ${D}/init
}

FILES:${PN} += "/init"