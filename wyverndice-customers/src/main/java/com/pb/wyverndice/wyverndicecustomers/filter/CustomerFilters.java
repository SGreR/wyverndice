package com.pb.wyverndice.wyverndicecustomers.filter;

import com.pb.wyverndice.wyverndicecustomers.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerFilters {
    private Optional<String> name = Optional.empty();
    private Optional<String> email = Optional.empty();
    private Optional<Role> role = Optional.empty();
}
