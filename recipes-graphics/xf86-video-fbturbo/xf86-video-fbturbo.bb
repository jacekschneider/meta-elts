SUMMARY = "X.Org driver for fbturbo framebuffer (optimized fbdev)"
DESCRIPTION = "A performance-tuned framebuffer driver for X.Org. \
Originally for Allwinner, but works generically on /dev/fb devices."

LICENSE = "MIT-X"
LIC_FILES_CHKSUM = "file://COPYING;md5=f91dc3ee5ce59eb4b528e67e98a31266"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/ssvb/xf86-video-fbturbo.git;protocol=http;branch=master "

S = "${WORKDIR}/git"

DEPENDS = "virtual/xserver xorgproto util-macros"

inherit autotools pkgconfig

# Remove broken .c file from Makefile.am before autotools runs
do_configure:prepend() {
    sed -i '/backing_store_tuner\.c/d' ${S}/src/Makefile.am
}

FILES:${PN} += "${libdir}/xorg/modules/drivers/*.so"
