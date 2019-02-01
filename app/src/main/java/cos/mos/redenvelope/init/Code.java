package cos.mos.redenvelope.init;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Description: <p>
 * @Author: Kosmos
 * @Date: 2019.02.01 13:53
 * @Email: KosmoSakura@gmail.com
 */
public interface Code {
    //原子状态
    AtomicBoolean status = new AtomicBoolean(false);

}
