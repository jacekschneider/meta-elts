SUMMARY = "Custom X11 configuration files"
DESCRIPTION = "Installs custom 99-calibration.conf and 99-fbturbo.conf files"
LICENSE = "CLOSED"

SRC_URI = "file://99-calibration.conf \
           file://99-fbturbo.conf \
           "

S = "${WORKDIR}"

do_install() {
    install -d ${D}/etc/X11/xorg.conf.d
    install -d ${D}/usr/share/X11/xorg.conf.d

    install -m 0644 ${S}/99-calibration.conf ${D}/etc/X11/xorg.conf.d/
    install -m 0644 ${S}/99-fbturbo.conf ${D}/usr/share/X11/xorg.conf.d/
}

FILES:${PN} += " \
    /etc/X11/xorg.conf.d/99-calibration.conf \
    /usr/share/X11/xorg.conf.d/99-fbturbo.conf \
"