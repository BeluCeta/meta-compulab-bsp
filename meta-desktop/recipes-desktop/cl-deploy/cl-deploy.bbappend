PACKAGE_ARCH = "${MACHINE_ARCH}"
RDEPENDS:${PN}:remove = "u-boot-fw-utils"
SRC_URI:remove = "file://cl-deploy.mtd"
COMPATIBLE_MACHINE = "(cl-som-imx8|ucm-imx8m-mini|mcm-imx8m-mini|iot-gate-imx8|mcm-imx8m-nano|ucm-imx8m-plus|som-imx8m-plus)"
