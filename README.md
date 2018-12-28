# locking-sample

This project is a locking experiment with several methods that have been considered. The purpose of this experiment is to find best practices in handling locking when there are many transactions.

Some methods included in the project:

- [x] Select ... For Update
- [x] Select count (*) and Insert
- [ ] Optimistic Locking (JPA)
- [ ] Optimistic Locking (Native)


**Select ... For Update**

Will lock the record with a write (exclusive) lock until the transaction is completed (committed or rolled back).


**Select count(\*) For Update**

The first process counts many rows in the container table, and if less than the stock limit is allowed for the transaction, otherwise will be rejected.


Case
-----

The case study that was tried was the management of items inventory that was accessed from many transactions, so that the quantity of items did not exceed the stock. Technically there are 2 fields, namely stock and current. Stock field is the total item available, and the current field is the number of items currently processed (starts from 0 and will to increase).

This experiment is run using jMeter, with a total of 600 samples (users 200, loop 3)

**Result**

**Select ... For Update**

<a href="https://drive.google.com/uc?export=view&id=1B72vBNqu-kFGf6HdO4q_pbUqaBVKnbBP"><img src="https://drive.google.com/uc?export=view&id=1B72vBNqu-kFGf6HdO4q_pbUqaBVKnbBP" style="width: 500px; max-width: 100%; height: auto" title="Click for the larger version." /></a>

<a href="https://drive.google.com/uc?export=view&id=1gEjSSdEjvAQttbYof3RnjumeqmDvHqvx"><img src="https://drive.google.com/uc?export=view&id=1gEjSSdEjvAQttbYof3RnjumeqmDvHqvx" style="width: 50px; max-width: 50%; height: 10px" title="Click for the larger version." /></a>

Between stock and current is the same.

**Select count(\*) and Insert**

<a href="https://drive.google.com/uc?export=view&id=1knZZ2kBJHJtHoqMb57RwSliK66OWLqTG"><img src="https://drive.google.com/uc?export=view&id=1knZZ2kBJHJtHoqMb57RwSliK66OWLqTG" style="width: 500px; max-width: 100%; height: auto" title="Click for the larger version." /></a>

<a href="https://drive.google.com/uc?export=view&id=1uZUk9vdv7zmRWkbX4LQRaiacjG2LwnjL"><img src="https://drive.google.com/uc?export=view&id=1uZUk9vdv7zmRWkbX4LQRaiacjG2LwnjL" style="width: 50px; max-width: 50%; height: 10px" title="Click for the larger version." /></a>

Total of items processed exceeds the stock
