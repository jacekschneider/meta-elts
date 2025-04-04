FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://10_eth0.network \
"

FILES:${PN} += " \
    ${sysconfdir}/systemd/network/10_eth0.network \
"

do_install:append() {
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/10_eth0.network ${D}${sysconfdir}/systemd/network
}
