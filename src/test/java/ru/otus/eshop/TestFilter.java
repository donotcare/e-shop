package ru.otus.eshop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.eshop.model.catalog.Category;
import ru.otus.eshop.model.catalog.CategoryRepository;
import ru.otus.eshop.model.catalog.Product;
import ru.otus.eshop.model.catalog.ProductRepository;
import ru.otus.eshop.model.process.filter.Filter;
import ru.otus.eshop.model.process.filter.FilterRepository;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class TestFilter {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FilterRepository filterRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void filter() {
        Category category = categoryRepository.findOne(1L);
        Filter filter = filterRepository.getFilterByCategory(category);
        List<Product> products = productRepository.findAll(filter.getRealFilter());
        assertThat(products.size(), is(2));
    }
}
