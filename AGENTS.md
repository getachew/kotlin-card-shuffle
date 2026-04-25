# Repo Review Guidance

This repository is intentionally small and dependency-light. Keep changes focused on correctness and clarity rather than abstraction.

## What Matters Most

- Preserve the simple executable Kotlin workflow based on `app.kt` and `tests.kt`.
- Prefer small, direct code changes over introducing frameworks, build tools, or extra layers.
- Treat deck behavior and dealing semantics as the main correctness surface.
- Flag logic changes that can break the 52-card deck assumptions, shuffle behavior, or card-dealing flow.

## Testing Expectations

- The project uses executable test functions in `tests.kt` instead of a larger test framework.
- Review comments should prefer adding or adjusting small unit-style test functions in `tests.kt`.
- If a change affects shuffle, dealing, or deck construction, ask for focused regression coverage in `tests.kt`.

## Review Tone

- Keep findings practical and repo-specific.
- Avoid generic advice about architecture, CI, or repo-wide process unless the PR clearly introduces that concern.
- Prefer comments tied to actual Kotlin code paths in `app.kt` or `tests.kt`.
