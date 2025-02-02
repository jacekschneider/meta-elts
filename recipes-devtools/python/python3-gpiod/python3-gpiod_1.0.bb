SUMMARY = "Python bindings for libgpiod"
DESCRIPTION = "This package provides Python bindings for libgpiod."
HOMEPAGE = "https://github.com/hhk7734/python3-gpiod"
LICENSE = "CLOSED"

SRC_URI = "git://github.com/hhk7734/python3-gpiod.git;protocol=https;branch=main"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit setuptools3

# Keep `python3-setuptools-native` only as a build-time dependency
DEPENDS = "python3 python3-setuptools-native python3-wheel python3-pip libgpiod swig"

# Remove `python3-setuptools-native` from runtime dependencies
RDEPENDS:${PN} = "python3"

do_install:append() {
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}
    cp -r ${S}/build/lib/gpiod ${D}${PYTHON_SITEPACKAGES_DIR}/
}