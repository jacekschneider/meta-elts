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
    # Conditional install of X11 calibration and display configs
        if [ "${SKIP_TFT35_X11_CONFIGS}" != "1" ]; then
        install -d ${D}/etc/X11/xorg.conf.d
        install -d ${D}/usr/share/X11/xorg.conf.d
    fi

    # Copy precompiled TFT35A Device Tree Blob
    install -m 0644 ${S}/usr/tft35a-overlay.dtb ${D}/boot/overlays/tft35a.dtbo


    # Conditional install of X11 calibration and display configs
    if [ "${SKIP_TFT35_X11_CONFIGS}" != "1" ]; then
        install -m 0644 ${S}/usr/99-calibration.conf-35-0 ${D}/etc/X11/xorg.conf.d/99-calibration.conf
        install -m 0644 ${S}/usr/99-fbturbo.conf ${D}/usr/share/X11/xorg.conf.d/
    fi

    # Copy boot and system configuration files
    install -m 0644 ${S}/usr/cmdline.txt ${D}/boot/cmdline.txt
}

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0664 ${S}/usr/tft35a-overlay.dtb ${DEPLOYDIR}/tft35a.dtbo
}

addtask deploy after do_install

FILES:${PN} = " \
    /boot/overlays/tft35a.dtbo \
    /boot/cmdline.txt \
    /etc/X11/xorg.conf.d \
    /usr/share/X11 \
    /usr/share/X11/xorg.conf.d \
"

INSANE_SKIP_${PN} += "installed-vs-shipped"

