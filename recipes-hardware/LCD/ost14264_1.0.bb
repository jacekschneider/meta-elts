SUMMARY = "Raspberry Pi LCD driver"
DESCRIPTION = "Performs OST-14264 lcd driver script installer"
LICENSE="CLOSED"

SRC_URI="file://LCD-show-160701.tar.gz"

S = "${WORKDIR}/LCD-show"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/LCD*-show ${D}${bindir}
    install -m 0755 ${S}/LCD-hdmi ${D}${bindir}
    install -m 0755 ${S}/LCD35-show ${D}${bindir}/LCD35-show
}

FILES_${PN} = "${bindir}/"

RDEPENDS_${PN} = "bash"

inherit allarch

