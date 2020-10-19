package com.njust.hsicloud.test.service;

import com.njust.hsicloud.core.feature.test.TestSupport;
import com.njust.hsicloud.web.model.SpecdataExample;
import com.njust.hsicloud.web.model.SpecdataWithBLOBs;
import com.njust.hsicloud.web.service.SpectralLibraryService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class SpectralDataServiceTest  extends TestSupport {
    @Resource
    private SpectralLibraryService spectralLibraryService;

    @Test
    public void test11() {
        SpecdataExample specdataExample = new SpecdataExample();
        specdataExample.or().andIdEqualTo(456);
        List<SpecdataWithBLOBs> list = spectralLibraryService.selectByExampleWithBLOBs(specdataExample);
        System.out.println(list.toString());
    }
}
