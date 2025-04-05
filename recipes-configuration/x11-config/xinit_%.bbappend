FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://xinitrc"

do_install:append() {
    install -m 0755 ${WORKDIR}/xinitrc ${D}${sysconfdir}/X11/xinit/xinitrc
}
