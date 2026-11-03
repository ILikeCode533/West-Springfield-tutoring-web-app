# Engineering Notes

## 1. UUID (Universally Unique Identifier)

**Definition:** A 128-bit identifier designed
to be practically unique.

**Purpose:** Identifies records without
relying on sequential numbers.

**Alternatives:** Auto-incrementing integers
and custom string IDs.

**Tradeoffs:** Difficult to guess and useful
across systems, but less readable and larger
than integers.

**Project example:** Each tutoring request
will have its own UUID.

