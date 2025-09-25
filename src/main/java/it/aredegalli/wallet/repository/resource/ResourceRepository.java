package it.aredegalli.wallet.repository.resource;

import it.aredegalli.wallet.entity.resource.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}

