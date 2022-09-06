package lt.markmerkk.ping.repositories

import lt.markmerkk.ping.dao.PingEntryDao
import lt.markmerkk.ping.entities.entries.PingEntry

class PingRepository(
    private val pingEntryDao: PingEntryDao,
) {
    fun storePing(
        pingEntry: PingEntry,
    ) {
        pingEntryDao.save(pingEntry)
    }
}