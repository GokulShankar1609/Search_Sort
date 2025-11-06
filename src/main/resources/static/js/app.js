let currentSteps = [];
let currentStepIndex = 0;

function showSection(sectionName) {
    const sections = document.querySelectorAll('.content-section');
    const navBtns = document.querySelectorAll('.nav-btn');
    
    sections.forEach(section => section.classList.remove('active'));
    navBtns.forEach(btn => btn.classList.remove('active'));
    
    document.getElementById(`${sectionName}-section`).classList.add('active');
    event.target.classList.add('active');
}

async function handleCSVUpload(type) {
    const fileInput = document.getElementById(`${type}-csv`);
    const file = fileInput.files[0];
    
    if (!file) return;
    
    const formData = new FormData();
    formData.append('file', file);
    
    try {
        const response = await fetch('/api/upload/csv', {
            method: 'POST',
            body: formData
        });
        
        const array = await response.json();
        document.getElementById(`${type}-array`).value = array.join(', ');
    } catch (error) {
        alert('Error uploading CSV file: ' + error.message);
    }
}

function parseArray(input) {
    return input.split(',').map(x => parseInt(x.trim())).filter(x => !isNaN(x));
}

async function runSearchAlgorithm(algorithm) {
    const arrayInput = document.getElementById('search-array').value;
    const targetInput = document.getElementById('search-target').value;
    
    const array = parseArray(arrayInput);
    const target = parseInt(targetInput);
    
    if (array.length === 0 || isNaN(target)) {
        alert('Please enter valid array and target values');
        return;
    }
    
    showLoading('search-visualization');
    
    try {
        const response = await fetch(`/api/search/${algorithm}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ array, target })
        });
        
        const result = await response.json();
        displaySearchResult(result);
    } catch (error) {
        alert('Error running algorithm: ' + error.message);
        document.getElementById('search-visualization').innerHTML = '';
    }
}

async function runSortAlgorithm(algorithm) {
    const arrayInput = document.getElementById('sort-array').value;
    const array = parseArray(arrayInput);
    
    if (array.length === 0) {
        alert('Please enter a valid array');
        return;
    }
    
    showLoading('sort-visualization');
    
    try {
        const response = await fetch(`/api/sort/${algorithm}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ array })
        });
        
        const result = await response.json();
        displaySortResult(result);
    } catch (error) {
        alert('Error running algorithm: ' + error.message);
        document.getElementById('sort-visualization').innerHTML = '';
    }
}

function showLoading(containerId) {
    const container = document.getElementById(containerId);
    container.innerHTML = `
        <div class="loading">
            <div class="loading-spinner"></div>
            <p>Processing algorithm...</p>
        </div>
    `;
}

function displaySearchResult(result) {
    currentSteps = result.steps;
    currentStepIndex = 0;
    
    const container = document.getElementById('search-visualization');
    container.innerHTML = `
        <div class="progress-info">
            <div style="flex: 1;">
                <h3>${result.algorithmName}</h3>
                <p>Step <span id="current-step">1</span> of ${result.steps.length}</p>
                <div class="progress-bar">
                    <div class="progress-fill" id="progress-fill" style="width: 0%"></div>
                </div>
            </div>
        </div>

        <div class="legend">
            <div class="legend-item">
                <div class="legend-color" style="background: linear-gradient(135deg, #ffd700 0%, #ffb700 100%);"></div>
                <span class="legend-label">Current Position</span>
            </div>
            <div class="legend-item">
                <div class="legend-color" style="background: linear-gradient(135deg, #ff6b6b 0%, #ee5a52 100%);"></div>
                <span class="legend-label">Comparing</span>
            </div>
            <div class="legend-item">
                <div class="legend-color" style="background: linear-gradient(135deg, #51cf66 0%, #40c057 100%);"></div>
                <span class="legend-label">Found</span>
            </div>
            <div class="legend-item">
                <div class="legend-color" style="background: linear-gradient(135deg, #a8b8d8 0%, #8892b0 100%);"></div>
                <span class="legend-label">Default</span>
            </div>
        </div>

        <div id="iteration-container"></div>
        <div id="array-display"></div>

        <div style="text-align: center; margin: 20px 0;">
            <button class="algo-btn" onclick="previousStep()" style="min-width: 120px;">⬅️ Previous</button>
            <button class="algo-btn" onclick="nextStep()" style="min-width: 120px;">Next ➡️</button>
        </div>

        <div class="complexity-card">
            <h3>📊 Algorithm Analysis</h3>
            <div class="complexity-grid">
                <div class="complexity-item">
                    <strong>Time Complexity:</strong>
                    <span>${result.timeComplexity}</span>
                </div>
                <div class="complexity-item">
                    <strong>Space Complexity:</strong>
                    <span>${result.spaceComplexity}</span>
                </div>
                <div class="complexity-item">
                    <strong>Execution Time:</strong>
                    <span>${result.executionTimeMs} ms</span>
                </div>
                <div class="complexity-item">
                    <strong>Result:</strong>
                    <span>${result.found ? 'Found at index ' + result.foundIndex : 'Not Found'}</span>
                </div>
            </div>
            <div style="background: rgba(255,255,255,0.15); padding: 15px; border-radius: 10px; margin-top: 15px;">
                <strong>Best Case:</strong> ${result.bestCase}<br>
                <strong>Average Case:</strong> ${result.averageCase}<br>
                <strong>Worst Case:</strong> ${result.worstCase}
            </div>
            <div style="background: rgba(255,255,255,0.15); padding: 15px; border-radius: 10px; margin-top: 15px;">
                <strong>Description:</strong> ${result.description}
            </div>
            ${result.recommendation ? `
                <div class="recommendation-box">
                    <h4>💡 Recommendation</h4>
                    <p>${result.recommendation}</p>
                </div>
            ` : ''}
        </div>
    `;
    
    displayStep();
}

function displaySortResult(result) {
    currentSteps = result.steps;
    currentStepIndex = 0;
    
    const container = document.getElementById('sort-visualization');
    container.innerHTML = `
        <div class="progress-info">
            <div style="flex: 1;">
                <h3>${result.algorithmName}</h3>
                <p>Step <span id="current-step">1</span> of ${result.steps.length}</p>
                <div class="progress-bar">
                    <div class="progress-fill" id="progress-fill" style="width: 0%"></div>
                </div>
            </div>
        </div>

        <div class="legend">
            <div class="legend-item">
                <div class="legend-color" style="background: linear-gradient(135deg, #ff8c42 0%, #ff6b35 100%);"></div>
                <span class="legend-label">Selected/Pivot</span>
            </div>
            <div class="legend-item">
                <div class="legend-color" style="background: linear-gradient(135deg, #ff6b6b 0%, #ee5a52 100%);"></div>
                <span class="legend-label">Comparing</span>
            </div>
            <div class="legend-item">
                <div class="legend-color" style="background: linear-gradient(135deg, #51cf66 0%, #40c057 100%);"></div>
                <span class="legend-label">Sorted</span>
            </div>
            <div class="legend-item">
                <div class="legend-color" style="background: linear-gradient(135deg, #a8b8d8 0%, #8892b0 100%);"></div>
                <span class="legend-label">Unsorted</span>
            </div>
        </div>

        <div id="iteration-container"></div>
        <div id="array-display"></div>

        <div style="text-align: center; margin: 20px 0;">
            <button class="algo-btn" onclick="previousStep()" style="min-width: 120px;">⬅️ Previous</button>
            <button class="algo-btn" onclick="nextStep()" style="min-width: 120px;">Next ➡️</button>
        </div>

        <div class="complexity-card">
            <h3>📊 Algorithm Analysis</h3>
            <div class="complexity-grid">
                <div class="complexity-item">
                    <strong>Time Complexity:</strong>
                    <span>${result.timeComplexity}</span>
                </div>
                <div class="complexity-item">
                    <strong>Space Complexity:</strong>
                    <span>${result.spaceComplexity}</span>
                </div>
                <div class="complexity-item">
                    <strong>Execution Time:</strong>
                    <span>${result.executionTimeMs} ms</span>
                </div>
                <div class="complexity-item">
                    <strong>Total Iterations:</strong>
                    <span>${result.steps.length}</span>
                </div>
            </div>
            <div style="background: rgba(255,255,255,0.15); padding: 15px; border-radius: 10px; margin-top: 15px;">
                <strong>Best Case:</strong> ${result.bestCase}<br>
                <strong>Average Case:</strong> ${result.averageCase}<br>
                <strong>Worst Case:</strong> ${result.worstCase}
            </div>
            <div style="background: rgba(255,255,255,0.15); padding: 15px; border-radius: 10px; margin-top: 15px;">
                <strong>Description:</strong> ${result.description}
            </div>
            ${result.recommendation ? `
                <div class="recommendation-box">
                    <h4>💡 Recommendation</h4>
                    <p>${result.recommendation}</p>
                </div>
            ` : ''}
        </div>
    `;
    
    displayStep();
}

function displayStep() {
    if (currentStepIndex < 0 || currentStepIndex >= currentSteps.length) return;
    
    const step = currentSteps[currentStepIndex];
    const progress = ((currentStepIndex + 1) / currentSteps.length) * 100;
    
    document.getElementById('current-step').textContent = currentStepIndex + 1;
    document.getElementById('progress-fill').style.width = progress + '%';
    
    document.getElementById('iteration-container').innerHTML = `
        <div class="iteration-info">
            <p><strong>Iteration ${step.iteration}:</strong> ${step.action}</p>
        </div>
    `;
    
    const arrayHtml = step.array.map((value, index) => {
        let className = 'default';
        
        if (step.sortedIndices && step.sortedIndices.includes(index)) {
            className = 'sorted';
        } else if (step.highlightIndices && step.highlightIndices.includes(index)) {
            className = 'highlight';
        } else if (step.comparisonIndices && step.comparisonIndices.includes(index)) {
            className = 'comparing';
        } else if (step.currentIndex !== null && step.currentIndex === index) {
            className = 'current';
        }
        
        if (step.targetIndex !== null && step.targetIndex === index) {
            className = 'found';
        }
        
        return `
            <div class="array-element">
                <div class="array-bar ${className}">
                    ${value}
                </div>
                <div class="index-label">[${index}]</div>
            </div>
        `;
    }).join('');
    
    document.getElementById('array-display').innerHTML = `
        <div class="array-container">
            ${arrayHtml}
        </div>
    `;
}

function nextStep() {
    if (currentStepIndex < currentSteps.length - 1) {
        currentStepIndex++;
        displayStep();
    }
}

function previousStep() {
    if (currentStepIndex > 0) {
        currentStepIndex--;
        displayStep();
    }
}
