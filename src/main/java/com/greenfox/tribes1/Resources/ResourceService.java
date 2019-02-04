package com.greenfox.tribes1.Resources;

import com.greenfox.tribes1.Building.Building;
import com.greenfox.tribes1.Exception.NotValidResourceException;
import com.greenfox.tribes1.Kingdom.Kingdom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {
  
  private ResourceRepository resourceRepository;
  
  @Autowired
  public ResourceService(ResourceRepository resourceRepository) {
    this.resourceRepository = resourceRepository;
  }
  
  public KingdomResource saveResource(KingdomResource kingdomResource) throws NotValidResourceException {
    if (validResource(kingdomResource)) {
      return resourceRepository.save(kingdomResource);
    }
    throw new NotValidResourceException("Resource validation failed");
  }
  
  public boolean validResource(KingdomResource resource) {
    return resource != null;
  }
  
  public void updateResource(Kingdom kingdom) {
    /*KingdomResource actualResource;
    for (int i = 0; i < kingdom.getResources().size(); i++) {
      actualResource = kingdom.getResources().get(i);
      if (actualResource instanceof Food) {
        actualResource.
            setAmount(actualResource.getAmount();
      }
      if (actualResource instanceof Gold) {
        actualResource.
            setAmount(actualResource.getAmount());
      }
    }*/
  }
  
  public Long resourceToAdd(Building building, Long passedTime, KingdomResource resource) {
    return 0L;
  }
}