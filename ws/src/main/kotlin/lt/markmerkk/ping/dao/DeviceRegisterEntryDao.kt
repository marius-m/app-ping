package lt.markmerkk.ping.dao

import lt.markmerkk.ping.entities.entries.DeviceRegisterEntry
import org.springframework.data.repository.CrudRepository

interface DeviceRegisterEntryDao: CrudRepository<DeviceRegisterEntry, Long>