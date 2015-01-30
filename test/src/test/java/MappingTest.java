import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.NameTokenizers;

public class MappingTest {

    private ModelMapper modelMapper;

    @Before
    public void setUp() throws Exception {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSourceNameTokenizer(NameTokenizers.UNDERSCORE);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testMapping() throws Exception {
        final LinkedList<Map> maps = new LinkedList<Map>();
        for (int i = 0; i < 6; i++) {
            final Map<String, String> map = new HashMap<String, String>();
            if (i < 3) {
                map.put("ETA_TEL", null);
                map.put("ETA_FAX", "fax" + i);
            } else {
                map.put("ETA_TEL", "tel" + i);
                map.put("ETA_FAX", "fax" + i);
            }
            maps.add(map);
        }
        List<TestDTO> tests = modelMapper.map(maps, new TypeToken<List<TestDTO>>() {
        }.getType());

        Assert.assertEquals(tests.size(), 6);
        for (int i = 0; i < 6; i++) {
            final TestDTO testDTO = tests.get(i);
            if (i < 3) {
                Assert.assertNull(testDTO.getEtaTel());
                Assert.assertEquals("fax" + i, testDTO.getEtaFax());
            } else {
                Assert.assertNotNull(testDTO.getEtaTel());
                Assert.assertEquals("tel" + i, testDTO.getEtaTel());
                Assert.assertEquals("fax" + i, testDTO.getEtaFax());

            }

        }
   }

    /**
     * @author : iguissouma
     */
    public static class TestDTO {
        private String etaTel;
        private String etaFax;

        public String getEtaTel() {
            return etaTel;
        }

        public void setEtaTel(String etaTel) {
            this.etaTel = etaTel;
        }

        public String getEtaFax() {
            return etaFax;
        }

        public void setEtaFax(String etaFax) {
            this.etaFax = etaFax;
        }
    }
}