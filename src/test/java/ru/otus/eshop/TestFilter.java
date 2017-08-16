package ru.otus.eshop;

import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.eshop.catalog.category.Category;
import ru.otus.eshop.catalog.category.CategoryRepository;
import ru.otus.eshop.catalog.product.Product;
import ru.otus.eshop.catalog.product.ProductRepository;
import ru.otus.eshop.catalog.property.Property;
import ru.otus.eshop.catalog.property.PropertyRepository;
import ru.otus.eshop.process.filter.Filter;
import ru.otus.eshop.process.filter.FilterCondition;
import ru.otus.eshop.process.filter.FilterRepository;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@RequiredArgsConstructor
public class TestFilter {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FilterRepository filterRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PropertyRepository propertyRepository;

    @Test
    public void filter() {
        Category category = categoryRepository.findOne(1L);
        Filter filter = filterRepository.getFilterByCategory(category);
        Property property = propertyRepository.findOne(1L);
        FilterCondition condition = filter.getConditionByProperty(property);
        condition.setValue("ЖК");
        List<Product> products = productRepository.findAll(filter);
        assertThat(products.size(), is(2));

        condition.setValue("WRONG VALUE");
        products = productRepository.findAll(filter);
        assertThat(products.size(), is(0));
    }
}
