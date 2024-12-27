FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " file://enable_spi_update.patch"

do_install:append () {
    install -m 0644 ${S}/etc/keapi/spi.conf ${D}/${sysconfdir}/keapi/spi.conf
}
