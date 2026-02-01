SUMMARY = "A console-only image with more full-featured Linux system \
functionality installed."

IMAGE_FEATURES += "splash ssh-server-openssh package-management"

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    packagegroup-core-full-cmdline \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    "

inherit core-image

hostname_pn-base-files = " elts"

IMAGE_INSTALL:append = " os-release"
IMAGE_INSTALL:append = " gdbserver"
IMAGE_INSTALL:append = " kernel-image kernel-devicetree"
IMAGE_INSTALL:append = " tcpdump iputils"
IMAGE_INSTALL:append = " nano"
IMAGE_INSTALL:append = " dtc"
IMAGE_INSTALL:append = " motd"

IMAGE_INSTALL:append = " kernel-devsrc"

IMAGE_INSTALL:append = " mcpioexpander"
IMAGE_INSTALL:append = " kernel-module-gpio-interrupt"
IMAGE_INSTALL:append = " modules-conf"


SKIP_TFT35_X11_CONFIGS = "1"
IMAGE_INSTALL:append = " lcd-show"

IMAGE_INSTALL:append = " i2c-tools"
IMAGE_INSTALL:append = " spidev-test"
IMAGE_INSTALL:append = " libgpiod libgpiod-tools libiio libcamera"

IMAGE_INSTALL:append = " xserver-xorg xinit xterm "
IMAGE_INSTALL:append = " packagegroup-core-x11"


