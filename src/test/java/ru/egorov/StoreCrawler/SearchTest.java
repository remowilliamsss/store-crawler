package ru.egorov.StoreCrawler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.egorov.StoreCrawler.dto.SearchRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-products-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-products-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class SearchTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void searchForProductAvailableInAllStores() throws Exception {
        SearchRequest searchRequest = new SearchRequest("Air Trainer 1");

        this.mockMvc.perform(post("/api/products/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(searchRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("foundProductList[0].differences[1]").exists());
    }

    @Test
    public void searchForProductOnlyAvailableOnSneakerhead() throws Exception {
        SearchRequest searchRequest = new SearchRequest("Nike Joyride ENV ISPA");

        this.mockMvc.perform(post("/api/products/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(searchRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("foundProductList[0].differences[0].storeName")
                        .value("Sneakerhead"))
                .andExpect(jsonPath("foundProductList[0].differences[1]").doesNotExist());
    }

    @Test
    public void searchForProductOnlyAvailableOnFootbox() throws Exception {
        SearchRequest searchRequest = new SearchRequest("Jordan Legacy 312 \"Exploration Unit\"");

        this.mockMvc.perform(post("/api/products/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(searchRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("foundProductList[0].differences[0].storeName")
                        .value("Footbox"))
                .andExpect(jsonPath("foundProductList[0].differences[1]").doesNotExist());
    }

    @Test
    public void searchForNotAvailableProduct() throws Exception {
        SearchRequest searchRequest = new SearchRequest("some query");

        this.mockMvc.perform(post("/api/products/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(searchRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("foundProductList").isEmpty());
    }

    @Test
    public void searchWithManyResults() throws Exception {
        SearchRequest searchRequest = new SearchRequest("nike");

        this.mockMvc.perform(post("/api/products/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(searchRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("foundProductList[3]").exists());
    }

    @Test
    public void searchForNullQuery() throws Exception {
        SearchRequest searchRequest = new SearchRequest(null);

        this.mockMvc.perform(post("/api/products/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(searchRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message").value("must not be empty"));
    }

    @Test
    public void searchForEmptyQuery() throws Exception {
        SearchRequest searchRequest = new SearchRequest("");

        this.mockMvc.perform(post("/api/products/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(searchRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message").value("must not be empty"));
    }

    @Test
    public void findBySkuForProductAvailableInAllStores() throws Exception {
        SearchRequest searchRequest = new SearchRequest("DR7515-200");

        this.mockMvc.perform(post("/api/products/find_by_sku")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(searchRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("foundProductList[0].differences[1]").exists());
    }

    @Test
    public void findBySkuForProductOnlyAvailableOnSneakerhead() throws Exception {
        SearchRequest searchRequest = new SearchRequest("BV4584-400");

        this.mockMvc.perform(post("/api/products/find_by_sku")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(searchRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("foundProductList[0].differences[0].storeName")
                        .value("Sneakerhead"))
                .andExpect(jsonPath("foundProductList[0].differences[1]").doesNotExist());
    }

    @Test
    public void findBySkuForProductOnlyAvailableOnFootbox() throws Exception {
        SearchRequest searchRequest = new SearchRequest("FB1875-141");

        this.mockMvc.perform(post("/api/products/find_by_sku")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(searchRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("foundProductList[0].differences[0].storeName")
                        .value("Footbox"))
                .andExpect(jsonPath("foundProductList[0].differences[1]").doesNotExist());
    }

    @Test
    public void findBySkuForNotAvailableProduct() throws Exception {
        SearchRequest searchRequest = new SearchRequest("some query");

        this.mockMvc.perform(post("/api/products/find_by_sku")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(searchRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("foundProductList").isEmpty());
    }

    @Test
    public void findBySkuForNullQuery() throws Exception {
        SearchRequest searchRequest = new SearchRequest(null);

        this.mockMvc.perform(post("/api/products/find_by_sku")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(searchRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message").value("must not be empty"));
    }

    @Test
    public void findBySkuForEmptyQuery() throws Exception {
        SearchRequest searchRequest = new SearchRequest("");

        this.mockMvc.perform(post("/api/products/find_by_sku")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(searchRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message").value("must not be empty"));
    }

    @Test
    public void findByStoreForSneakerhead() throws Exception {
        this.mockMvc.perform(get("/api/products/sneakerhead"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[4]").exists());
    }

    @Test
    public void findByStoreWithPaginationForSneakerhead() throws Exception {
        this.mockMvc.perform(get("/api/products/sneakerhead")
                        .param("page", "0")
                        .param("page_size", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1]").exists())
                .andExpect(jsonPath("$[2]").doesNotExist());
    }

    @Test
    public void findByStoreForFootbox() throws Exception {
        this.mockMvc.perform(get("/api/products/footbox"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[4]").exists());
    }

    @Test
    public void findByStoreWithPaginationForFootbox() throws Exception {
        this.mockMvc.perform(get("/api/products/footbox")
                        .param("page", "1")
                        .param("page_size", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1]").exists())
                .andExpect(jsonPath("$[2]").doesNotExist());
    }

    @Test
    public void findByStoreForNotSupportedStore() throws Exception {
        this.mockMvc.perform(get("/api/products/some_store"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("message").value("The store with this name is not supported"));
    }

    @Test
    public void findByStoreWithPaginationForNotSupportedStore() throws Exception {
        this.mockMvc.perform(get("/api/products/some_store")
                        .param("page", "0")
                        .param("page_size", "2"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("message").value("The store with this name is not supported"));
    }
}