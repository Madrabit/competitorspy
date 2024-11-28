package ru.madrabit.competitorspy.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.madrabit.competitorspy.dto.ProductProgramDTOResp;
import ru.madrabit.competitorspy.entity.Product;

import java.util.List;

@Service
public class ProductService {
    public List<Product> getAll() {
        return null;
    }

    public ProductProgramDTOResp getProgamById(long l) {
        return null;
    }

    public Page<Product> getProductPaginated(int page, int size) {
        return null;
    }
}
