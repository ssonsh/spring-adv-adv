package hello.advanced.temp;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class TempTest {

    @Test
    void test() {

        Set<Long> orgRqIds = Sets.newHashSet();
        orgRqIds.add(1L);
        orgRqIds.add(2L);
        orgRqIds.add(3L);
        orgRqIds.add(4L);
        orgRqIds.add(5L);

        Set<Long> currentOrgIds = Sets.newHashSet();
        currentOrgIds.add(1L);
        currentOrgIds.add(2L);
        currentOrgIds.add(3L);
        currentOrgIds.add(4L);
        currentOrgIds.add(5L);
        currentOrgIds.add(6L);


        List<Organization> organizations = Lists.newArrayList();
        organizations.add(new Organization(1L, "1번"));
        organizations.add(new Organization(2L, "2번"));
        organizations.add(new Organization(3L, "3번"));
        organizations.add(new Organization(4L, "4번"));
        organizations.add(new Organization(5L, "5번"));
        organizations.add(new Organization(6L, "6번"));


        if(!orgRqIds.containsAll(currentOrgIds)){
            List<Long> notIncludedOrgIds = currentOrgIds.stream().filter(orgId -> !orgRqIds.contains(orgId)).collect(Collectors.toList());
            List<String> notIncludedOrgNames = organizations.stream()
                                                            .filter(org -> notIncludedOrgIds.contains(org.getId()))
                                                            .map(Organization::getName)
                                                            .collect(Collectors.toList());
            log.info("error!!!!!!!!!!!!!!");
            notIncludedOrgIds.forEach(System.out::println);
            notIncludedOrgNames.forEach(System.out::println);
        }

    }

    @Data
    private class Organization{
        private Long id;
        private String name;

        public Organization(){

        }
        public Organization(Long id, String name){
            this.id = id;
            this.name = name;
        }
    }
}
