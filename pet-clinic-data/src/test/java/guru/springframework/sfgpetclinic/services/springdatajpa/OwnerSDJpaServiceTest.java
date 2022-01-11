package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final String LAST_NAME = "smith";
    public static final Long OWNER_ID = 101L;

    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    Owner returnOwner;

    @BeforeEach
    void setup() {
        returnOwner = Owner.builder().id(OWNER_ID).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        // Given
        when(ownerRepository.findByLastName( any() )).thenReturn(returnOwner);

        // When
        Owner actualOwner = service.findByLastName(LAST_NAME);

        // Then
        assertNotNull(actualOwner);
        assertTrue(actualOwner.getLastName().matches(LAST_NAME));
        verify(ownerRepository).findByLastName( any() );
    }

    @Test
    void findAll() {
        // Given
        Set<Owner> returnOwnerSet = new HashSet<>();
        returnOwnerSet.add(Owner.builder().id(1L).build());
        returnOwnerSet.add(Owner.builder().id(2L).build());
        when(ownerRepository.findAll()).thenReturn(returnOwnerSet);

        // When
        Set<Owner> actualOwnerSet = service.findAll();

        // Then
        assertNotNull(actualOwnerSet);
        assertEquals(2, actualOwnerSet.size());
    }

    @Test
    void findById() {
        // Given
        when(ownerRepository.findById( anyLong() )).thenReturn(Optional.of(returnOwner));

        // When
        Owner actualOwner = service.findById(OWNER_ID);

        // Then
        assertNotNull(actualOwner);
        assertTrue(actualOwner.getLastName().matches(LAST_NAME));
        assertEquals(OWNER_ID, actualOwner.getId());
        verify(ownerRepository).findById( any() );
    }

    @Test
    void findByIdNotFound() {
        // Given
        when(ownerRepository.findById( anyLong() )).thenReturn(Optional.empty());

        // When
        Owner actualOwner = service.findById(OWNER_ID);

        // Then
        assertNull(actualOwner);
    }

    @Test
    void save() {
        // Given
        Owner ownerToSave = Owner.builder().id(202L).build();
        when(ownerRepository.save(any())).thenReturn(ownerToSave);

        // When
        Owner actualOwner = service.save(ownerToSave);

        // Then
        assertNotNull(actualOwner);
        verify(ownerRepository).save(any()); // default is 1 times
    }

    @Test
    void delete() {
        // Given

        // When
        service.delete(returnOwner);

        // Then
        verify(ownerRepository).delete(any()); // default is 1 times
    }

    @Test
    void deleteById() {
        // Given

        // When
        service.deleteById(OWNER_ID);

        // Then
        verify(ownerRepository).deleteById(anyLong()); // default is 1 times
    }
}