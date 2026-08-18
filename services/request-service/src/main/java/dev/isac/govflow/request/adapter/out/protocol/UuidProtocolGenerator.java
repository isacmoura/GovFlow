package dev.isac.govflow.request.adapter.out.protocol;

import java.time.Year;
import java.util.UUID;

import org.springframework.stereotype.Component;

import dev.isac.govflow.request.application.port.out.ProtocolGenerator;

@Component
class UuidProtocolGenerator implements ProtocolGenerator {

  @Override
  public String nextProtocol() {
    String suffix = UUID.randomUUID()
      .toString()
      .substring(0, 8)
      .toUpperCase();

    
    return "REQ-" + Year.now().getValue() + "-" + suffix;
  }

}
