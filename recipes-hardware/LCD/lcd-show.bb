SUMMARY = "Install configuration files for TFT35 display"
LICENSE = "CLOSED"

SRC_URI = "git://github.com/goodtft/LCD-show.git;protocol=https;branch=master"

SRCREV = "${AUTOREV}"
PV = "1.0"

S = "${WORKDIR}/git"

inherit deploy

do_install() {
    # Create necessary directories
    install -d ${D}/boot/overlays
    install -d ${D}/etc/X11/xorg.conf.d
    install -d ${D}/usr/share/X11/xorg.conf.d

    # Copy precompiled TFT35A Device Tree Blob
    install -m 0644 ${S}/usr/tft35a-overlay.dtb ${D}/boot/overlays/tft35a.dtb
    install -m 0644 ${S}/usr/tft35a-overlay.dtb ${D}/boot/overlays/tft35a.dtbo

    # Copy X11 calibration and display configs
    install -m 0644 ${S}/usr/99-calibration.conf-35-0 ${D}/etc/X11/xorg.conf.d/99-calibration.conf
    install -m 0644 ${S}/usr/99-fbturbo.conf ${D}/usr/share/X11/xorg.conf.d/

    # Copy boot and system configuration files
    install -m 0644 ${S}/usr/cmdline.txt ${D}/boot/cmdline.txt
    install -m 0644 ${S}/usr/inittab ${D}/etc/inittab
}

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0664 ${S}/usr/tft35a-overlay.dtb ${DEPLOYDIR}/tft35a.dtbo
}

addtask deploy after do_compile


FILES:${PN} = " \
    /boot/overlays/tft35a.dtb \
    /boot/overlays/tft35a.dtbo \
    /etc/X11/xorg.conf.d/99-calibration.conf \
    /usr/share/X11/xorg.conf.d/99-fbturbo.conf \
    /boot/cmdline.txt \
    /etc/inittab \
"


INSANE_SKIP_${PN} += "installed-vs-shipped"

