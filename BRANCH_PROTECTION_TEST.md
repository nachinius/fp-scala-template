# Branch Protection Test

This file demonstrates that branch protection rules are working correctly.

## What Should Happen

1. ✅ PR created with auto-merge enabled
2. ⏳ GitHub Actions must run and pass:
   - `build` (from ci.yml)
   - `Scala CI / build` (from scala.yml)
3. ✅ Only after all checks pass, PR will auto-merge
4. 🚫 Cannot merge manually until checks pass

## Branch Protection Rules Configured

- **Required Status Checks:** `build`, `Scala CI / build`
- **Strict Mode:** Branches must be up to date
- **Pull Request Required:** Cannot push directly to main
- **Force Push:** Disabled
- **Branch Deletion:** Disabled

This test will be deleted after demonstrating the functionality.
