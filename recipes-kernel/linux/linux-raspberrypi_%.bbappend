FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
file://i2c.cfg \
file://inkernel_config.cfg \
"

SRC_URI += "file://custom-mcp23017-overlay.dts;subdir=git/arch/${ARCH}/boot/dts/overlays \
            "
