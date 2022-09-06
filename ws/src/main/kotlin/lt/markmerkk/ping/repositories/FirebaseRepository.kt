package lt.markmerkk.ping.repositories

import lt.markmerkk.ping.dao.DeviceRegisterEntryDao
import lt.markmerkk.ping.entities.entries.DeviceRegisterEntry
import org.slf4j.LoggerFactory

class FirebaseRepository(
    private val deviceRegisterDao: DeviceRegisterEntryDao
) {
    fun deviceRegister(
        deviceRegisterEntry: DeviceRegisterEntry,
    ) {
        l.debug("deviceRegister(entry: {})", deviceRegisterEntry)
        deviceRegisterDao.save(deviceRegisterEntry)
    }

    fun deviceUnregister(
        token: String,
    ): Boolean {
        val deviceByToken = deviceRegisterDao.findByToken(token)
        return if (deviceByToken != null) {
            l.debug("deviceUnregister(token: {}, entry: {})", token, deviceByToken)
            deviceRegisterDao.delete(deviceByToken)
            true
        } else {
            l.debug("deviceUnregister.failureNoEntry(token: {})", token)
            false
        }
    }

    fun deviceList(): List<DeviceRegisterEntry> {
        l.debug("deviceList()")
        return deviceRegisterDao.findAll().toList()
    }

    fun deviceClearAll() {
        l.debug("deviceClearAll()")
        deviceRegisterDao.deleteAll()
    }

    companion object {
        private val l = LoggerFactory.getLogger(FirebaseRepository::class.java)!!
    }
}