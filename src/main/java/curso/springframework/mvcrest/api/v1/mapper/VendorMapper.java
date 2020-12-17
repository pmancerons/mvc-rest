package curso.springframework.mvcrest.api.v1.mapper;

import curso.springframework.mvcrest.api.v1.model.VendorDTO;
import curso.springframework.mvcrest.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VendorMapper {
    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor source);

    Vendor vendorDTOToVendor(VendorDTO source);
}
