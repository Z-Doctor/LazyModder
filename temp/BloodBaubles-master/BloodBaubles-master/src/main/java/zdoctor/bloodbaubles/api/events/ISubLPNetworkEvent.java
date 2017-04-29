package zdoctor.bloodbaubles.api.events;

import zdoctor.bloodbaubles.token.EventToken;
import zdoctor.bloodbaubles.token.NetworkDrainToken;

public interface ISubLPNetworkEvent<T extends EventToken> extends ISubEvent<T> {

  public static interface ISubLPDrain
      extends
        ISubLPNetworkEvent<NetworkDrainToken> {
  }
}
