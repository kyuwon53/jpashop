package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Album;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ItemServiceTest {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void 상품_저장() throws Exception {
        //given
        Item album = new Album();
        album.setName("test album");

        //when
        itemRepository.save(album);

        //then
        assertThat(album).isEqualTo(itemRepository.findOne(album.getId()));
    }
}