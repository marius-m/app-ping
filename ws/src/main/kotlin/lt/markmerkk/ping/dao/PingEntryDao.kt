package lt.markmerkk.ping.dao

import lt.markmerkk.ping.entities.PingEntry
import org.springframework.data.repository.CrudRepository

interface PingEntryDao: CrudRepository<PingEntry, Long>