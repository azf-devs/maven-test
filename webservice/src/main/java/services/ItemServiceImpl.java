package services;

import entities.Item;
import exceptions.EntityNotFoundException;
import exceptions.InvalidEntityException;
import lombok.RequiredArgsConstructor;
import repositories.ItemRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item create(Item item) {
        validateItem(item);
        return itemRepository.save(item);
    }

    @Override
    public Item update(int id, Item item) {
        validateItem(item);
        Item oldItem = findByIdOrFail(id);
        oldItem.setLabel(item.getLabel());
        oldItem.setDate(item.getDate());
        return itemRepository.save(oldItem);
    }

    private void validateItem(Item item) {
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        if (!violations.isEmpty()) {
            throw new InvalidEntityException();
        }
    }

    @Override
    public void delete(int id) {
        this.itemRepository.delete(id);
    }

    @Override
    public Item findByIdOrFail(int id) {
        return itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
