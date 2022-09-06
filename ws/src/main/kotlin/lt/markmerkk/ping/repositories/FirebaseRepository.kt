package lt.markmerkk.ping.repositories

import lt.markmerkk.ping.dao.DeviceRegisterEntryDao
import lt.markmerkk.ping.entities.entries.DeviceRegisterEntry

class FirebaseRepository(
    private val deviceRegisterDao: DeviceRegisterEntryDao
) {
    fun deviceRegister(
        deviceRegisterEntry: DeviceRegisterEntry,
    ) {
        deviceRegisterDao.save(deviceRegisterEntry)
    }

    fun deviceList(): List<DeviceRegisterEntry> {
        return deviceRegisterDao.findAll().toList()
    }

    fun deviceClearAll() {
        deviceRegisterDao.deleteAll()
    }
}