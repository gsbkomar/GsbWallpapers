package gsbkomar.domain.models

import gsbkomar.domain.models.tree.Results

interface Category {
    val results: List<Results>
}