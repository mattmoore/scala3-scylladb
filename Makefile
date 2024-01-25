.PHONY: scylla-start
scylla-start: ## Start scylla
	docker/scylla-start.sh

.PHONY: scylla-stop
scylla-stop: ## Stop scylla
	docker/scylla-stop.sh

.PHONY: scylla-schema
scylla-schema: ## Load scylla schema
	docker/scylla-schema.sh

.PHONY: scylla-status
scylla-status: ## Print scylla status
	docker/scylla-status.sh

.PHONY: scylla-cli
scylla-cli: ## Connect to CQLSH
	docker/scylla-cli.sh

help: ## Prints this help message
	@grep -h -E '^[a-zA-Z0-9\._-]+:.*?## .*$$' $(MAKEFILE_LIST) |\
		sort | \
		awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
.DEFAULT_GOAL := help
