LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${WORKDIR}/README;md5=20919b3b0882c4ea49630574a50dd4e8"

DEPENDS = "u-boot-mkimage-native"

SRC_URI = " \
    file://README \
    file://boot.script \
"

do_compile() {
    mkimage -C none -A arm -T script -d "${WORKDIR}/boot.script" boot.scr
}

do_install() {
    install -d ${D}/boot
    install -m 0644 boot.scr ${D}/boot/boot.scr
    install -m 0644 ${WORKDIR}/boot.script ${D}/boot/boot.script
}

inherit deploy

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0644 boot.scr ${DEPLOYDIR}
}

addtask do_deploy after do_compile before do_build

FILES:${PN} += "/boot/"

RPROVIDES:${PN} += "u-boot-ai-script"

PACKAGE_ARCH = "${MACHINE_ARCH}"
